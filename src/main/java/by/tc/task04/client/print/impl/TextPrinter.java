package by.tc.task04.client.print.impl;

import by.tc.task04.client.print.GeneralPrinter;
import by.tc.task04.entity.Text;

public class TextPrinter implements GeneralPrinter
{
    private Text textToPrint;

    public TextPrinter(Text text)
    {
        textToPrint = text;
    }

    public void print()
    {
        System.out.println(textToPrint.getContent());
    }

    public void setTextToPrint(Text textToPrint)
    {
        this.textToPrint = textToPrint;
    }
}
