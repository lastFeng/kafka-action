package com.springboot.springmvc.view.pdfExport;

/***omit imports***/

public interface PdfExportService{
	public void make(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}