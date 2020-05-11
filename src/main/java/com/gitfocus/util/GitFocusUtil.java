package com.gitfocus.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gitfocus.git.db.model.Units;
import com.gitfocus.repository.UnitsRepository;

/**
 * @author Tech Mahindra 
 * Common utility class for GitFocus-Service Application
 */
@Service
public class GitFocusUtil {

    private final static Logger logger = LoggerFactory.getLogger(GitFocusUtil.class);

    public GitFocusUtil() {
        super();
        logger.info("GitFocusUtil init");
    }

    @Autowired
    private UnitsRepository uRepository;

    String accessToken = null;
    RestTemplate restTemplate = null;
    ResponseEntity<String> jsonResponse = null;
    HttpHeaders headers = null;
    HttpEntity<String> entity = null;

    /**
     * Method to add AcceessToken in HTTPHeader
     * 
     * @param jsonURL
     * @return jsonResponse
     */
    public String getGitAPIJsonResponse(String jsonURL) {
        List<Units> units = uRepository.findAll();
        units.forEach(response -> {
            if (response.getUnitName().equalsIgnoreCase("TR")) {
                accessToken = response.getAccessToken();
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "Bearer " + accessToken);
                entity = new HttpEntity<String>(headers);
                restTemplate = new RestTemplate();
                jsonResponse = restTemplate.exchange(jsonURL, HttpMethod.GET, entity, String.class);
            }
        });
        return jsonResponse.getBody();

    }

    /**
     * Method to convert String to Date
     * 
     * @param date
     * @return sDate
     * @throws ParseException
     */
    public static Date stringToDate(String date) {
        logger.info("Date is  " + date);
        Timestamp timestamp = null;
        Date parsedDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * Method to get start and endDate
     * 
     * @param date
     * @return Date[]
     */
    public static Date[] getStartAndEndDate(String date) {
        logger.info("Date is  " + date);
        Date startDate = null;
        Date endDate = null;
        String newDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            startDate = sdf.parse(date);

            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            c.add(Calendar.DAY_OF_MONTH, 1);
            newDate = sdf.format(c.getTime());
            endDate = sdf.parse(newDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Date[] { startDate, endDate };
    }
}