package com.example.job_platform.Security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import com.example.job_platform.Entity.PaidSubscription;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceGenerator {
	
	public static ByteArrayInputStream generateInvaoice(PaidSubscription sub) {
		
		Document doc = new Document();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			
			PdfWriter.getInstance(doc, out);
			doc.open();
			
			Font titeFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);
			Paragraph title= new Paragraph("Subscription Invoice",titeFont);
			title.setAlignment(Element.ALIGN_CENTER);
			doc.add(title);
			doc.add(new Paragraph(" "));
			
			
			
		     PdfPTable table = new PdfPTable(2);
		     
		     table.setWidthPercentage(80);
		     table.setSpacingBefore(20);
		     
		     table.addCell("Subscription ID");
		     table.addCell(sub.getId().toString());
		     
		     table.addCell("Recruiter Id");
		     table.addCell(sub.getRecruiterId().toString());
		     
		     table.addCell("Employee ID");
		     table.addCell(sub.getEmployeeId().toString());
		     
		     table.addCell("Plan ID");
		     table.addCell(sub.getPlanId().toString());
		     
		     table.addCell("Amount");
		     table.addCell(sub.getAmount().toString());
		     
		     table.addCell("currency");
		     table.addCell(sub.getCurrency().toString());
		     
		     table.addCell("Payment Status");
		     table.addCell(sub.getPaymentStatus().toString());
		     
		     table.addCell("Start Date");
		     table.addCell(sub.getStartDate().format(DateTimeFormatter.ISO_DATE));
		    
		     table.addCell("End Date");
		     table.addCell(sub.getEndDate().format(DateTimeFormatter.ISO_DATE));
		     
		     doc.add(table);
		     doc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}

}
