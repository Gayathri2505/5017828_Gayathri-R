package document;

class WordDocument implements Document
{
	@Override
    public void open() {
        System.out.println("Word document");
    }
}
class PdfDocument implements Document
{
	@Override
    public void open() {
        System.out.println("Pdf document");
    }
}

class ExcelDocument implements Document
{
	@Override
    public void open() {
        System.out.println("Excel document");
    }
}