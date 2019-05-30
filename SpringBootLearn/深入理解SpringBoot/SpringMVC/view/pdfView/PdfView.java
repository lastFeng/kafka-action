package com.springboot.springmvc.view.pdf;

/***omit imports***/

public class PdfView extends AbstractPdfView{
	// 导出服务接口
	private PdfExportService pdfExportService;
	
	// 创建对象时载入导出服务接口
	public PdfView(PdfExportService pdfExportService){
		this.pdfExportService = pdfExportService;
	}
	
	// 接口实现
	@Override
	public void buildPdfDocument((Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception{
		pdfExportService.make(model, document, writer, request, response);
	}
}