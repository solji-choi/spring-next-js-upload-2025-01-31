package com.ll.standard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.global.app.AppConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Ut {
    public static class str {
        public static boolean isBlank(String str) {
            return str == null || str.trim().isEmpty();
        }
    }

    public static class json {
        private static final ObjectMapper om = AppConfig.getObjectMapper();

        @SneakyThrows
        public static String toString(Object obj) {
            return om.writeValueAsString(obj);
        }
    }

    public static class jwt {
        public static String toString(String secret, long expireSeconds, Map<String, Object> body) {
            Date issuedAt = new Date();
            Date expiration = new Date(issuedAt.getTime() + 1000L * expireSeconds);

            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

            String jwt = Jwts.builder()
                    .claims(body)
                    .issuedAt(issuedAt)
                    .expiration(expiration)
                    .signWith(secretKey)
                    .compact();

            return jwt;
        }

        public static boolean isValid(String secret, String jwtStr) {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

            try {
                Jwts
                        .parser()
                        .verifyWith(secretKey)
                        .build()
                        .parse(jwtStr);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        public static Map<String, Object> payload(String secret, String jwtStr) {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

            try {
                return (Map<String, Object>) Jwts
                        .parser()
                        .verifyWith(secretKey)
                        .build()
                        .parse(jwtStr)
                        .getPayload();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class file {
        private static final Map<String, String> MIME_TYPE_MAP = new LinkedHashMap<>() {{
            put("application/json", "json");
            put("text/plain", "txt");
            put("text/html", "html");
            put("text/css", "css");
            put("application/javascript", "js");
            put("image/jpeg", "jpg");
            put("image/png", "png");
            put("image/gif", "gif");
            put("image/webp", "webp");
            put("image/svg+xml", "svg");
            put("application/pdf", "pdf");
            put("application/xml", "xml");
            put("application/zip", "zip");
            put("application/gzip", "gz");
            put("application/x-tar", "tar");
            put("application/x-7z-compressed", "7z");
            put("application/vnd.rar", "rar");
            put("audio/mpeg", "mp3");
            put("audio/wav", "wav");
            put("video/mp4", "mp4");
            put("video/webm", "webm");
            put("video/x-msvideo", "avi");
        }};


        @SneakyThrows
        public static String downloadByHttp(String url, String dirPath) {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            String tempFilePath = dirPath + "/" + UUID.randomUUID() + ".tmp";

            Ut.file.mkdir(dirPath);

            // 실제 파일 다운로드
            HttpResponse<Path> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofFile(Path.of(tempFilePath))
            );

            // 파일 확장자 추출
            String extension = getExtensionFromResponse(response);

            if (extension.equals("tmp")) {
                extension = getExtensionByTika(tempFilePath);
            }

            // 파일명 추출
            String filename = getFilenameFromUrl(url);

            String newFilePath = dirPath + "/" + filename + "." + extension;

            mv(tempFilePath, newFilePath);

            return newFilePath;
        }

        public static String getExtensionByTika(String filePath) {
            String mineType = AppConfig.getTika().detect(filePath);
            return MIME_TYPE_MAP.getOrDefault(mineType, "tmp");
        }

        @SneakyThrows
        private static void mv(String oldFilePath, String newFilePath) {
            Files.move(
                    Path.of(oldFilePath),
                    Path.of(newFilePath),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        @SneakyThrows
        private static void mkdir(String dirPath) {
            Path path = Path.of(dirPath);

            if (Files.exists(path)) return;

            Files.createDirectories(path);
        }

        private static String getFilenameFromUrl(String url) {
            try {
                String path = new URI(url).getPath();
                String filename = Path.of(path).getFileName().toString();
                // 확장자 제거
                return filename.contains(".")
                        ? filename.substring(0, filename.lastIndexOf('.'))
                        : filename;
            } catch (URISyntaxException e) {
                // URL에서 파일명을 추출할 수 없는 경우 타임스탬프 사용
                return "download_" + System.currentTimeMillis();
            }
        }

        private static String getExtensionFromResponse(HttpResponse<?> response) {
            return response.headers()
                    .firstValue("Content-Type")
                    .map(contentType -> MIME_TYPE_MAP.getOrDefault(contentType, "tmp"))
                    .orElse("tmp");
        }

        @SneakyThrows
        public static void delete(String filePath) {
            Files.deleteIfExists(Path.of(filePath));
        }
    }

    public static class cmd {
        public static void runAsync(String cmd) {
            new Thread(() -> {
                run(cmd);
            }).start();
        }

        public static void run(String cmd) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", "-c", cmd);
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                // 프로세스의 출력 읽기
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // IntelliJ 콘솔에 출력
                }

                // 에러 출력 읽기
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line); // 에러 메시지를 IntelliJ 콘솔에 출력
                }

                process.waitFor(1, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
