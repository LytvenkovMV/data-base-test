package org.example.staticdata;

import org.example.staticdata.belorussian.BelorussianFemaleFIO;
import org.example.staticdata.belorussian.BelorussianMaleFIO;
import org.example.staticdata.russian.RussianFemaleFIO;
import org.example.staticdata.russian.RussianMaleFIO;
import org.example.staticdata.ukranian.UkranianFemaleFIO;
import org.example.staticdata.ukranian.UkranianMaleFIO;

import java.util.List;

public class FIOLists {
    public static final List<AbstractFIO> MALE_FIO_LIST = List.of(
            new RussianMaleFIO(),
            new UkranianMaleFIO(),
            new BelorussianMaleFIO()
    );

    public static final List<AbstractFIO> FEMALE_FIO_LIST = List.of(
            new RussianFemaleFIO(),
            new UkranianFemaleFIO(),
            new BelorussianFemaleFIO()
    );
}
