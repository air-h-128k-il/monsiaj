=begin
= 実行方法

== Java2 SE 1.4

 $ java \
         -jar dist/pandaclient.jar \
         -host=localhost \
         -user=sample \
         -pass=sample \
         panda:helloworld

== Java2 SE 1.3

 $ XMLLIBS=/usr/share/java/xercesImpl.jar:/usr/share/java/xmlParserAPIs.jar
 $ java \
         -cp dist/pandaclient.jar:$XMLLIBS \
         -host=localhost \
         -user=sample \
         -pass=sample \
         panda:helloworld

= プロパティ

: monsia.logger.factory
  ロギングに使用するクラスを指定。設定できるのは以下の4通り。
  : org.montsuqi.util.NullLogger
    一切ログを出力しない。
  : org.montsuqi.util.StdErrLogger
    標準エラー出力に出力。
  : org.montsuqi.util.Log4JLogger
    ApacheのLog4Jを使用。
  : org.montsuqi.util.J2SELogger
    Java2 SDK 1.4のロギングAPIを使用。
: swing.defaultlaf
  ルック&フィールを指定。
  : com.sun.java.swing.plaf.windows.WindowsLookAndFeel
    Windows風(Java2 SDK 1.4ではXP風)
  : com.sun.java.swing.plaf.motif.MotifLookAndFeel
    Motif風
  などが設定可能。
=end
