package com.data2discovery.sts.controllers;

import com.data2discovery.sts.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by mike on 11/16/15.
 *
 * Use this to upload via curl:
 *   curl -i -F file=@REF_BLGCL_RSLT_TYP.txt http://localhost:8080/files/upload
 */
@Slf4j
@RestController
@RequestMapping(value = "/files")
@Transactional
public class DataLoaderController {

    public static final String GENE_TISSUE_INSERT = "insert into gene_tissue values(?,?)";
    public static final String GENE_INSERT = "insert into gene values(?,?,?)";
    public static final String TISSUE_INSERT = "insert into tissue values(?,?)";
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private EntityManager entityManager;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> loadFileContent(MultipartHttpServletRequest multiPartRequest) throws IOException {
        log.debug("Uploading file");
        String filename = multiPartRequest.getFiles("file").get(0).getOriginalFilename();
        String fname = multiPartRequest.getFileNames().next();
        InputStream istream = multiPartRequest.getFile(fname).getInputStream();

        if (fname.equals("gene.csv")) {
            processGeneFile(istream);
        } else if (fname.equals("tissue.csv")) {
            processTissueFile(istream);
        } else if (fname.equals("gene_tissue.csv")) {
            processGeneTissueFile(istream);
        } else if (filename.equals("REF_STS.txt")) {
            processRefSts(istream);
        } else if (filename.equals("REF_UOM.txt")) {
            processRefUOM(istream);
        } else if (filename.equals("REF_BLGCL_RSLT_TYP.txt")) {
            processRefBiologicalResultType(istream);
        } else if (filename.equals("REF_RSLT_LVL.txt")) {
            processRefResultLvl(istream);
        } else if (filename.equals("REF_VLD_UOM.txt")) {
            processRefVldUom(istream);
        } else {
            return ResponseEntity.badRequest().body("No recognized file uploaded.");
        }
        return ResponseEntity.ok("File processed and data loaded");
    }

    private void processRefVldUom(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'))
        ) {
            List<CSVRecord> records = parser.getRecords();
            records.stream().forEach(rec -> {
                int recCount = 0;
                RefVldUomEntity entity = new RefVldUomEntity();
                String x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    entity.setRefVldUomId(Short.parseShort(x));
                }
                x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    entity.setRefUomId(Short.parseShort(x));
                }
                x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    entity.setRefBlgclRsltTypId(Short.parseShort(x));
                }
                x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    entity.setRefStsId(Short.parseShort(x));
                }
                entity.setStsDt(new Time(new Date().getTime()));
                entity.setChngTmstmp(new Time(new Date().getTime()));
                log.debug(entity.toString());
                entityManager.persist(entity);
            });
            entityManager.flush();
            log.debug("Done loading RefDb file: {}", records.size());
        }
    }

    private void processRefResultLvl(InputStream istream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(istream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'))
        ) {
            List<CSVRecord> records = parser.getRecords();
            records.stream().forEach(rec -> {
                int recCount = 0;
                RefRsltLvlEntity entity = new RefRsltLvlEntity();
                String x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    entity.setRefRsltLvlId(Integer.parseInt(x));
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    entity.setRsltLvlNm(x);
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    entity.setRsltLvlShrtNm(x);
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    entity.setRsltLvlDescTxt(x);
                }
                entity.setStsDt(new Time(new Date().getTime()));
                entity.setChngTmstmp(new Time(new Date().getTime()));
                log.debug(entity.toString());
                entityManager.persist(entity);
            });
            entityManager.flush();
            log.debug("Done loading RefDb file: {}", records.size());
        }
    }

    private void processRefBiologicalResultType(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'))
        ) {
            List<CSVRecord> records = parser.getRecords();
            records.stream().forEach(rec -> {
                int recCount = 0;
                RefBlgclRsltTypEntity bioResType = new RefBlgclRsltTypEntity();
                String x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    bioResType.setRefBlgclRsltTypId(Integer.parseInt(x));
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    bioResType.setBlgclRsltTypNm(x);
                } else {
                    bioResType.setBlgclRsltTypNm("Not provided");
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    bioResType.setBlgclRsltTypShrtNm(x);
                } else {
                    bioResType.setBlgclRsltTypShrtNm("Not provided");
                }
                x = rec.get(recCount++);
                if (StringUtils.isNotEmpty(x)) {
                    bioResType.setBlgclRsltTypDescTxt(x);
                } else {
                    bioResType.setBlgclRsltTypDescTxt("Not Provided");
                }
                x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    bioResType.setRefStsId(Integer.parseInt(x));
                }
                x = rec.get(recCount++);
                if (x.length() > 30) {
                    bioResType.setSumMdTxt(x.substring(30));
                } else {
                    bioResType.setSumMdTxt(x);
                }
                x = rec.get(recCount++);
                if (StringUtils.isNumeric(x)) {
                    bioResType.setRefRsltLvlId(Integer.parseInt(x));
                }
                bioResType.setChngTmstmp(new Time(new Date().getTime()));
                bioResType.setStsDt(new Time(new Date().getTime()));
                log.debug(bioResType.toString());
                entityManager.persist(bioResType);
            });
            entityManager.flush();
            log.debug("Done loading RefDb file: {}", records.size());
        }


    }

    private void processGeneTissueFile(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader())
        ) {
            List<CSVRecord> records = parser.getRecords();
            int[] inserted = jdbcTemplate.batchUpdate(GENE_TISSUE_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            CSVRecord csvRecord = records.get(i);
                            ps.setObject(1, csvRecord.get(0));
                            ps.setInt(2, Integer.parseInt(csvRecord.get(1)));
                        }

                        @Override
                        public int getBatchSize() {
                            return records.size();
                        }
                    });
            log.debug("Done loading Gene-Tissue file: {}", inserted.length);
        }
    }

    private void processTissueFile(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader())
        ) {
            List<CSVRecord> records = parser.getRecords();
            int[] inserted = jdbcTemplate.batchUpdate(TISSUE_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            CSVRecord csvRecord = records.get(i);
                            ps.setInt(1, Integer.parseInt(csvRecord.get(0)));
                            ps.setString(2, csvRecord.get(1));
                        }

                        @Override
                        public int getBatchSize() {
                            return records.size();
                        }
                    });
            log.debug("Done loading Tissue file: {}", inserted.length);
        }
    }

    private void processGeneFile(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader())
        ) {
            List<CSVRecord> records = parser.getRecords();
            int[] inserted = jdbcTemplate.batchUpdate(GENE_INSERT,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            CSVRecord csvRecord = records.get(i);
                            ps.setString(1, csvRecord.get(0));
                            ps.setString(2, csvRecord.get(1));
                            ps.setString(3, csvRecord.get(2));
                        }

                        @Override
                        public int getBatchSize() {
                            return records.size();
                        }
                    });
            log.debug("Done loading Gene file: {}", inserted.length);
        }
    }

    private void processRefSts(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'))
        ) {
            List<CSVRecord> records = parser.getRecords();
            records.stream().forEach(rec -> {
                int recCount = 0;
                RefStsEntity stsEntity = new RefStsEntity();
                stsEntity.setRefStsId(Integer.parseInt(rec.get(recCount++)));
                stsEntity.setStsNm( rec.get(recCount++) );
                stsEntity.setStsShrtNm( rec.get(recCount++) );
                stsEntity.setStsDescTxt( rec.get(recCount++) );
                stsEntity.setChngTmstmp(new Date());
                log.debug(stsEntity.toString());
                entityManager.persist(stsEntity);
            });
            entityManager.flush();
            log.debug("Done loading REF_STS file: {}", records.size());
        }
    }

    private void processRefUOM(InputStream inputStream) throws IOException {
        try (
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                CSVParser parser = new CSVParser(breader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'))
        ) {
            List<CSVRecord> records = parser.getRecords();
            records.stream().forEach(rec -> {
                int recCount = 0;
                RefUomEntity uomEntity = new RefUomEntity();
                uomEntity.setRefUomId(Integer.parseInt(rec.get(recCount++)));
                uomEntity.setUomNm(rec.get(recCount++));
                uomEntity.setUomShrtNm(rec.get(recCount++));
                uomEntity.setUomDescTxt(rec.get(recCount++));
                uomEntity.setRefStsId(Integer.parseInt(rec.get(recCount++)));
                uomEntity.setChngTmstmp(new Date());
                uomEntity.setStsDt(new Date());
                entityManager.persist(uomEntity);
            });
            entityManager.flush();
            log.debug("Done loading REF_STS file: {}", records.size());
        }
    }
}
