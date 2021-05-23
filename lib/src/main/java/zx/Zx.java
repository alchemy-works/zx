package zx;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class Zx {

    public static @NotNull CompletableFuture<String> $(@NotNull String s) {
        try {
            var process = new ProcessBuilder()
                    .command(s.split("\s"))
                    .redirectErrorStream(true)
                    .start();
            return process.onExit().thenApply(it -> {
                //
                return readBytesToString(it::getInputStream);
            });
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static @NotNull String readBytesToString(@NotNull Supplier<@NotNull InputStream> iss) {
        try {
            return new String(iss.get().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
