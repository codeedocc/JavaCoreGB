package GeekBrains.JavaCore.JavaCoreLesson2;

public class Main {
    public static void main(String[] args) throws MyArraySizeException {

        String[][] strings1 = {
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
    };

    String[][] strings2 = {
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
    };

    String[][] strings3 = {
        {"a", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
        {"7", "7", "7", "7"},
    };

    try {
    System.out.println(sumArray(strings1));
    } catch (MyArraySizeException | MyArrayDataException e) {
        e.printStackTrace();
    }

    try {
        System.out.println(sumArray(strings2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    try {
         System.out.println(sumArray(strings3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if(array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
              try {
                sum += Integer.parseInt(array[i][j]);
              } catch (NumberFormatException e) {
                  throw new MyArrayDataException("Ошибка в ячейке : " + i + ", " + j);
              }
            }
        }

        return sum;
    }
    
}
