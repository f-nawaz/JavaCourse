//package javonet;
//
//import com.javonet.Javonet;
//import com.javonet.JavonetException;
//import com.javonet.api.NObject;
//
///**
// * Created by User on 06/06/2016.
// */
//public class Class1 {
//    public static void main(String[] args) {
//        try {
//            NObject mathsInstance = Javonet.New("Maths");
//            boolean result = mathsInstance.invoke("IsPrime",17);
//            System.out.println(result);
//
//            NObject contactRepositoryInstance = Javonet.New("ContactRepository");
//            NObject[] refArray = contactRepositoryInstance.invoke("GetContacts");
//            for (int i=0; i<refArray.length;i++)
//            {
//                String name = refArray[i].get("Name");
//                System.out.println(name);
//            }
//        } catch (JavonetException e) {
//            e.printStackTrace();
//        }
//    }
//}
