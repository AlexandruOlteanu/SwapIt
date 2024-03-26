package com.swapit.searchEngine.config;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Configuration("searchEngineConfiguration")
public class ApplicationConfig {

    @Value("${searchEngine.stopWordsFilepath}")
    private String filePath;
    @Value("${searchEngine.dictionary.path}")
    private String dictionaryPath;

    private static final Integer DEFAULT_SIZE = 100;

    @Bean(name = "standardAnalyzer")
    public StandardAnalyzer getStandardAnalyzer() throws FileNotFoundException {
        CharArraySet charArraySet = new CharArraySet(DEFAULT_SIZE, true);
        Pattern pattern = Pattern.compile("[\\w']+");
        File file = ResourceUtils.getFile(filePath);
        String fp = file.getAbsolutePath();
        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                pattern.matcher(line).results()
                        .forEach(matchResult -> charArraySet.add(matchResult.group()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new StandardAnalyzer(charArraySet);
    }

    @Bean
    public Directory getDirectory() throws IOException {
        return FSDirectory.open(Paths.get(dictionaryPath));
    }

}
