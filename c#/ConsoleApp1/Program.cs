using System;
using System.Collections;

class MainClass
{

    public static void Main(string[] args)
    {
        bool boolVariable = true;
        int intVariable = 10;
        float floatVariable = 3.4f;
        char charVariable = 'a';
        double dolubeVarable = 4.6;

        var longstr = """
            dfsssssssssssssssssssss
            dsffffffffffffffes
            sfeeeeeeeeeeegEgfe
            gsegsegege
            """;
        Console.WriteLine(longstr); 

        var pt = (X: 1, Y: 2);

        var subscript = (A: 0, B: 0);
        subscript = pt;
        pt.X = 5;
        Console.WriteLine(subscript.A);
        Console.WriteLine(pt.X);


        var slope = (double)pt.Y / (double)pt.X;
        Console.WriteLine($"A line from the origin to the point {pt} has a slope of {slope}.");
        var pt2 = pt with { Y = 10 };
        Console.WriteLine($"The point 'pt2' is at {pt2}.");

        string greeting = "      Hello World!       ";
        Console.WriteLine($"[{greeting}]");

        string trimmedGreeting = greeting.TrimStart();
        Console.WriteLine($"[{trimmedGreeting}]");

        trimmedGreeting = greeting.TrimEnd();
        Console.WriteLine($"[{trimmedGreeting}]");

        trimmedGreeting = greeting.Trim();
        Console.WriteLine($"[{trimmedGreeting}]");

        Console.WriteLine(Console.ReadLine());
        Console.WriteLine(boolVariable);
        Console.WriteLine(intVariable);
        Console.WriteLine(floatVariable);
        Console.WriteLine(charVariable);
        Console.WriteLine(dolubeVarable);
        Console.WriteLine(new Test().Methodf());
        new ArrayList();

    }
}

class Test
{
    private String a;
    public String Methodf()
    {
        return "df";

    }
}
