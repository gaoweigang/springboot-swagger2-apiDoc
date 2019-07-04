
import com.gwg.demo.Application;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;
/**
 * 生成格式文档的类，测试必须先启动项目再运行测试类，生成HTML的 mvn指令：mvn asciidoctor:process-asciidoc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class Swagger2MarkupTest {

    // 接口数据json访问路径
    private final String URL = "http://localhost:8080/v2/api-docs";

    /**
     * 生成AsciiDocs格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateAsciiDocs() throws Exception {
        // 输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC).withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS).withGeneratedExamples()
                .withoutInlineSchema().build();

        Swagger2MarkupConverter.from(new URL(URL)).withConfig(config).build()
                .toFolder(Paths.get("./docs/asciidoc/generated"));
    }

    /**
     * 生成Markdown格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        // 输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN).withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS).withGeneratedExamples()
                .withoutInlineSchema().build();

        Swagger2MarkupConverter.from(new URL(URL)).withConfig(config).build()
                .toFolder(Paths.get("./docs/markdown/generated"));
    }


    /**
     * 生成Confluence格式文档
     *
     * @throws Exception
     */
    @Test
    public void generateConfluenceDocs() throws Exception {
        // 输出Confluence使用的格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .withOutputLanguage(Language.ZH).withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples().withoutInlineSchema().build();

        Swagger2MarkupConverter.from(new URL(URL)).withConfig(config).build()
                .toFolder(Paths.get("./docs/confluence/generated"));
    }

    /**
     * 生成AsciiDocs格式文档,并汇总成一个文件
     *
     * @throws Exception
     */
    @Test
    public void generateAsciiDocsToFile() throws Exception {
        // 输出Ascii到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC).withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS).withGeneratedExamples()
                .withoutInlineSchema().build();

        Swagger2MarkupConverter.from(new URL(URL)).withConfig(config).build()
                .toFile(Paths.get("./docs/asciidoc/generated/all"));
    }

    /**
     * 生成Markdown格式文档,并汇总成一个文件
     *
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocsToFile() throws Exception {
        // 输出Markdown到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN).withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS).withGeneratedExamples()
                .withoutInlineSchema().build();

        Swagger2MarkupConverter.from(new URL(URL)).withConfig(config).build()
                .toFile(Paths.get("./docs/markdown/generated/all"));
    }

    @Test
    public void generateDosc() throws Exception {
        generateAsciiDocs();
        generateMarkdownDocs();
        generateConfluenceDocs();
        generateAsciiDocsToFile();
        generateMarkdownDocsToFile();
    }

}