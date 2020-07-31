package com.hlj.sql.z99999;


import com.fasterxml.jackson.databind.JsonNode;
import com.hlj.sql.z99999.utils.JdbcUtils;
import com.hlj.sql.z99999.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class InitToDb {

    @Test
    public void insert() throws IOException {
        File file = new File("D:\\study\\common-arith\\src\\main\\java\\com\\hlj\\sql\\z99999\\leetcode.json");
        String json = IOUtils.toString(new FileInputStream(file), "utf-8");
        JsonNode root = JsonUtils.objectMapper.readTree(json);
        JsonNode headersNode = root.get("headers");
        JsonNode allRowsNode = root.get("rows");
        Iterator<String> keys = headersNode.fieldNames();
        while (keys.hasNext()) {
            String key = keys.next();
            JsonNode rowsNode = allRowsNode.get(key);
            for (JsonNode rowNode : rowsNode) {
                //数字变成 '1'也可以插入
                List<String> objects = JsonUtils.jsonToArray(rowNode.toString(), String.class);
                String val = objects.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","));
                String sql = "insert into " + key + " values ( " + val + ")";
                JdbcUtils.insert(sql);
            }
        }
    }
}
