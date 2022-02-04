import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public static Logger getLog(String name) {
        // loggerの生成
        Logger logger = Logger.getLogger(name);

        try {
            // ハンドラーの生成（true:書き込み,false:上書き）
            Handler handler = new FileHandler("/sample.log", false);
            // SimpleFormatよるlogフォーマットの指定
            handler.setFormatter(new SimpleFormatter());
            // ログレベルの指定/ハンドラーの設定
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);

            // 標準出力の設定
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.CONFIG);
            logger.addHandler(consoleHandler);

            // 親ロガーの設定(true:親ロガーへ送信,false:親ロガーへ送信しない)
            logger.setUseParentHandlers(false);
            // ログ出力
            logger.finest("FNST");
            logger.finer("FNR");
            logger.fine("FN");
            logger.config("CFG");
            logger.info("INF");
            logger.warning("WNG");
            logger.severe("SVR");

            throw new IOException();

        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.CONFIG, "エラー発生", e);
            logger.warning("WNG:エラー発生");
        }
        return logger;
    }
}