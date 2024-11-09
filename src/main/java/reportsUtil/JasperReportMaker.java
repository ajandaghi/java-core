package reportsUtil;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.*;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JasperReportMaker {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, DRException {
        String reportPath = "E:\\java-core\\src\\main\\resources\\report.jrxml";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/jasper", "root", "AliJandaghi");
        //Class.forName("com.mysql.jdbc.Driver");
        //net.sf.jasperreports.engine.JasperReport jr = JasperCompileManager.compileReport(reportPath);

        JasperReportBuilder report = DynamicReports.report();
        report.columns(
                        Columns.column("Id", "id", DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.CENTER),
                        Columns.column("name", "name", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER),
                        Columns.column("user", "user", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER)).
                title(//title of the report
                        Components.text("SimpleReportExample")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())
                .setDataSource("select * from test",con);
        report.show();


    }
}
