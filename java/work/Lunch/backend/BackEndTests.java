import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class BackEndTests {

    String responseBody;
    int status;
    CloseableHttpClient httpclient = HttpClients.createDefault();
    ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        public String handleResponse(final HttpResponse response) throws IOException {
            status = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        }
    };

    @Test
    public void testResponseLogIn200() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/login")
                .setParameter("name", "Hrach")
                .setParameter("password", "231996")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 200);
        assertEquals(responseBody, "");
    }

    @Test
    public void testResponseLogIn404() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/login")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 404);
    }

    @Test
    public void testResponseGetOrderList200() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .setParameter("sessionId","id")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 200);
        assertEquals(responseBody, "{}");
    }

    @Test
    public void testResponseGetOrderList204() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .setParameter("sessionId","id")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 204);
    }

    @Test
    public void testResponseGetOrderList401() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .setParameter("sessionId","invalidId")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 401);
    }

    @Test
    public void testResponseGetOrderList404() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 404);
    }   

    @Test
    public void testResponseGetPlaces200() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 200);
        assertEquals(responseBody, "");
    }

    @Test
    public void testResponseGetPlaces404() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .serParameter("a", "A")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 404);
    }

    @Test
    public void testResponseGetProducts200() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 200);
        assertEquals(responseBody, "");
    }

    @Test
    public void testResponseGetProducts404() throws Exception {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("192.168.33.64:8080")
                .setPath("lunchOrder-1/getOrderList")
                .setParameter("a", "A")
                .build();
            HttpGet httpget = new HttpGet(uri);
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        assertEquals(status, 404);
    }

}
