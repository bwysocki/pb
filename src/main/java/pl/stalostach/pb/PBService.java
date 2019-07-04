package pl.stalostach.pb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import pl.stalostech.proto.HeaderOuterClass;
import pl.stalostech.proto.HeaderOuterClass.Header;
import pl.stalostech.proto.SearchRequestOuterClass;
import pl.stalostech.proto.SearchRequestOuterClass.SearchRequest;

@Service
public class PBService {
    
    public static final String path = "./../testme";
    
    public void testPB() {
        System.out.println("Testing PB");
        
        Header h = HeaderOuterClass.Header.newBuilder().setName("x").setValue("b").build();
        
        SearchRequest sr = SearchRequestOuterClass.SearchRequest.newBuilder().setPageNumber(123).setQuery("/ai/search")
                                                                .setResultPerPage(100).addQueryParams("a")
                                                                .addQueryParams("b")
                                                                .setHeader(h).build();
        
        System.out.println(sr);
        
        byte[] bytes = sr.toByteArray();
        

        try (FileOutputStream stream = new FileOutputStream(path)) {
            stream.write(bytes);
        } catch (Exception e) {
            System.out.println("Can not save to file :(");
        }
        
        try {
            byte[] readBytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Can not read from file :(");
        }
        
        System.out.println("After serialization and deserialization");
        System.out.println(sr);

    }

}
