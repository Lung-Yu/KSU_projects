
enum century{
     Tenth=10,
     Eleventh,
     Twelfth,
     Thirteenth,
     Fourteenth,
     Fifteenth,
     Sixteenth,
     Seventeenth,
     Eighteenth,
     Nineteen, 
     Twentieth,
     Twenty_first,//21
     Twenty_second//22
     };
typedef struct student
       {
        char name[100];
        char Nationality[30];
        char school[50];
        char status[500];
        enum century Century;
        int score_start_year;
        double score_start_MD;
        int score_end_year;              
        double socre_end_MD;
       }STU;
#define filename "psychologydata.txt"
/*��psychology data ��*//* 
     century      Name         �ͩ�~       ���~      ���y      �Ǭ�          ���� 
    |Nineteen | |���S      | |1832,08.26| |1920,08.31| |�w��   | |���c�D�q    | |�Ш|�Ǯa                                               |
    |Nineteen | |��i��    | |1842,01.11| |1910,08.26| |����   | |����D�q    | |�Ш|�Ǯa                                               |
    |Nineteen | |�إ�      | |1878,01.09| |1958,09.25| |����   | |�欰�D�q    | |�欰�D�q�߲z�ǳЩl�H                                   |
    |Nineteen | |������    | |1886,00.00| |1959,00.00| |����   | |�s�欰�D�q  | |�H��s�欰�߲z�ǵۺ�                                   |
    |Nineteen | |�򬥥�w  | |1856,05.06| |1939,09.23| |���a�Q | |�믫���R    | |�믫���R�Ǭ����Щl�H                                   |
    |Nineteen | |�a��      | |1875,07.26| |1961,06.06| |��h   | |�s�믫���R  | |�믫���R��v&���R�߲z�ǳЩl��                          |
    |Twentieth| |������    | |1908,04.01| |1970,06.08| |����   | |�H���D�q    | |���X�ĦX�믫���R�߲z��&�欰�D�q�߲z�Ǫ��H���D�q�߲z��  |
    |Twentieth| |ù�Ǵ�    | |1902,01.08| |1987,02.04| |����   | |�H���D�q    | |�H���D�q�߲z�Ǫ��Щl�H���@�D���ɫǿԸߪ��Щl�H         |
    |Nineteen | |�֨ȳ�    | |1896,08.09| |1980,09.06| |��h   | |�֨ȳ�      | |�ൣ�߲z�Ǯa                                           |
    |Nineteen | |�¯S���q  | |1880,04.15| |1980,10.21| |�w��   | |�榡��      | |1912�إ߮榡��(���ξǬ�)                               |

*/ 
