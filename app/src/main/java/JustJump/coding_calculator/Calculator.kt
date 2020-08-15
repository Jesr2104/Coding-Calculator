package JustJump.coding_calculator

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.hamzaahmedkhan.spinnerdialog.callbacks.OnSpinnerOKPressedListener
import com.github.hamzaahmedkhan.spinnerdialog.enums.SpinnerSelectionType
import com.github.hamzaahmedkhan.spinnerdialog.models.SpinnerModel
import com.github.hamzaahmedkhan.spinnerdialog.ui.SpinnerDialogFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_calculator.*
import java.text.DecimalFormat

/***************************************************************************/
// Funciones de la calculadora
/***************************************************************************/

//    Numero [0-9]
//      1. cuando se pulsen tendras que agregar el numero pulsado a la expresion [Ready]
//    Simbolos [+,-,*,/]
//      1. cuando se pulse se trendra que agregar el simbolo pulsado [Ready]
//      2. Si pulsas un simbolo y ya presede cualquier otro simbolo antes eliminas el ultimo y lo cambiar por el nuevo pulsado [Ready]
//      3. Si tenemos (- Se podra cambiar el simbolo solo eliminandolo a positivo nunca a otro simbolo como (* /) [Ready]
//      4. si la exprecion esta vacia no deberas colocar ningun simbolo [Ready]
//    Parentecis [()]
//      1. los parentecis podran ser colocado sin ninguna restriccion siempre y cuando cada parenteci abierto este cerrado [Ready]
//      2. si colocas un parentecias y antes se encuentras un parentecis se miltiplicara [Ready]//
//    Punto [.]
//      1. al precionar el boton del punto deberamos agregar el punto al lado del numero para combertirlo en decimales [Ready]
//      2. si precionamos el punto y no es un numero el caracte de su izquierda debera asumir que es 0. [Ready]
//      3. Evitar que el numero se le puedan poner mas de un punto por ejemplo 5.5.5 [Ready]
//      4. Si colocas puntos y el caracter antesesor es un parentecis ) se colocara ')*0.' [Ready]
//      5. Permiter poner numero pequeños [Ready]
//    Boton [+/-]
//      1. si la exprecion esta vacia debera agregar la opcion de ingregar un numero negativo (- [Ready]
//      2. si precionamos sobre un numero positivo ya escrito en la expresion deberemos volverlo en negativo [Ready]
//      3. si pulsamos por segunda ves cuando ya el numero esta en negativo con esta estructuras '(-8' deberemos pasarlo para que continue estando en positivo [Ready]
//      4. Incluir numeros decimales para poder colocar en negativo [Ready]
//    Percentage [%]
//      1. este boton funciona como tan simple como dividir el resultado o numero individual entre 100 [Ready]
//      2. evitar poder escribir dos veces el mismo simbolo % [Ready]
//      3. evitar poder poner un % seguido de un parentecis abierto [Ready]
//      4. calcular el % de los que este en los parentecis [Ready]
//    Analizador de exprecion
//      1. el analizador comprobara que la exprecion es correcta [Ready]
//    Resultado [=]
//      1. cuando se preciona obtener resultado esperamos que se evelue la expresion [Ready]
//      2. obtenemos el resultado y lo visualizamos en el panel de resultados [Ready]
//    Resultados anteriores
//      1. este boton funcionare de tal manera que se muestrar las 5 ultimas expreciones y resultados en forma de lista [Ready]
//      2. Se guandaran en un array bidimencional donde estara expresion y resultado y seleccionandolo podremos ponerlo en nuestro campo de expresion [Ready]
//    Restringir
//      1. restringir el numero de decimales mostrados en la ventana a 7 en ambos campos []

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var state:Boolean = false

        /***************************************************************************/
        // Extra functions
        /***************************************************************************/
        fun check_integer(result_check: String):String
        {
            if (result_check[result_check.length - 1] == '0'){
                if (result_check[result_check.length - 2] == '.') {
                    return result_check.substring(0, result_check.length - 2)
                }
            }
            return result_check
        }

        fun clear_expression()
        {
            if(state)
            {
                expression.setText("")
                state=false
            }
        }

        fun ansData()
        {
            if(state)
            {
                expression.setText(tresult.text)
                tresult.setText("")
                state=false
            }
        }

        fun paintstring(expression: String): String
        {
            var resultemp: String = ""
            var cont:Int = 0

            while(cont < expression.length)
            {
                if(expression[cont] == '('|| expression[cont] == ')')
                {
                    resultemp = resultemp + "<font color=#868686>" + expression[cont] + "</font>"
                }
                else if(expression[cont] == '+'|| expression[cont] == '-'|| expression[cont] == '*'|| expression[cont] == '/'|| expression[cont] == '%' )
                {
                    // me gustaria cambien para integrar un numero negativo por completo en blanco like -9 y que no se viera en verde el signo
                    resultemp = resultemp + "<font color=#FFDD00>" + expression[cont] + "</font>"
                }
                else
                {
                    resultemp = resultemp + expression[cont]
                }
                cont++
            }

            return resultemp
        }

        // Guarda los historiales de acuaciones
        fun saveHistory(newData:String)
        {
            var shareref_serializardata = getSharedPreferences("historydata", Context.MODE_PRIVATE)
            var gson = Gson()
            var maxdata = 20

            // comprobacion de que el fichero 'historydata' existe
            if(shareref_serializardata.contains("historydata"))
            {
                var json = shareref_serializardata.getString("historydata", "DEFAULT")
                var savehisttory = gson.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java!!)

                // comprobamos que el numero de historiales guardados no supera al maximo establecido
                if (savehisttory.size >= maxdata)
                {
                    var temp: ArrayList<String> = ArrayList()
                    var i:Int = 2
                    var tempString: String = ""

                    while (i< maxdata)
                    {
                        temp.add(savehisttory[i])
                        i++
                    }

//                    i=0
//                    while(newData.length > i)
//                    {
//                        if (newData[i] == ',')
//                        {
//
//                        }
//                        else
//                        {
//                            tempString = tempString + newData[i]
//                        }
//                        i++
//                    }
                    temp.add(newData)

                    savehisttory = temp
                }
                else
                {
                    savehisttory.add(newData)
                }

                val editor = shareref_serializardata.edit()
                json = gson.toJson(savehisttory)
                editor.putString("historydata", json)
                editor.commit()

            }
            // si no existe el fichero se crea y se guarda la primera informacion
            else
            {
                var savehisttory = ArrayList<String>()
                savehisttory.add(newData)

                var json: String = ""
                json = gson.toJson(savehisttory)

                val editor = shareref_serializardata.edit()

                editor.putString("historydata", json)
                editor.commit()
            }
        }

        // carga la lista de historiales
        fun loadHistory(): ArrayList<String>?
        {
            var shareref_serializardata = getSharedPreferences("historydata", Context.MODE_PRIVATE)
            var gson = Gson()
            var savehisttory: ArrayList<String> = ArrayList()
            if(shareref_serializardata.contains("historydata"))
            {
                var json = shareref_serializardata.getString("historydata", "DEFAULT")
                savehisttory = gson.fromJson<ArrayList<String>>(json, ArrayList<String>()::class.java!!)

                return savehisttory
            }
            return savehisttory
        }

        //funcion para controlar cuando se inserta algun numero
        fun checkzero(number:Char)
        {
            var nueva = ""
            var controldecimal: Boolean = true

            if(expression.text.isNotEmpty())
            {
                var cont: Int = expression.text.length - 1
                var checkvalue: Boolean = false

                while(cont >= 0)
                {
                    if (expression.text[cont].isDigit() || expression.text[cont] == '.')
                    {
                        nueva=nueva+expression.text[cont]
                    }
                    else
                    {
                        cont = 0
                    }
                    cont --
                }

                for (item in nueva)
                {
                    if(item == '.')
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + number)))
                        controldecimal = false
                    }
                }

                if (controldecimal)
                {
                    // compruebo que para poner algun otro cero
                    while(cont >= 0)
                    {
                        if (expression.text[cont].isDigit())
                        {
                            if(Character.getNumericValue(expression.text[cont]) > 0)
                            {
                                checkvalue = true
                            }
                        }
                        else
                        {
                            cont = 0
                        }
                        cont --
                    }

                    if(checkvalue)
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + number)))
                    }
                    else if((expression.text[expression.text.length-1] == '0'))
                    {
                        cont = expression.text.length-1
                        while(cont >= 0)
                        {
                            if (expression.text[cont].isDigit() || expression.text[cont] == '.')
                            {
                                nueva=nueva+expression.text[cont]
                            }
                            else
                            {
                                cont = 0
                            }
                            cont --
                        }

                        if (nueva.substring(0, nueva.length - 1).toDouble() > 0)
                        {
                            expression.setText(Html.fromHtml(paintstring(expression.text.toString() + number)))
                        }
                        else
                        {
                            expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length-1) + number)))
                        }
                    }
                    else
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + number)))
                    }
                }
            }
            else
            {
                expression.setText(Html.fromHtml(paintstring(expression.text.toString() + number)))
            }
        }

        fun checkParentesis(Data: String): Boolean
        {
            var numeroparentecis: Int = 0
            // recorre el array de caracteres mientras busca parentecis
            for (item in Data)
            {
                if(item == '(')
                {
                    numeroparentecis++
                }

                if(item == ')')
                {
                    numeroparentecis--
                }
            }

            if (numeroparentecis != 0)
            {
                return false
            }
            return true
        }

        fun checknumberchar()
        {
            if(expression.length() > 10)
            {
                expression.setTextSize(25F)
            }
        }

        fun checksizeoftext()
        {
            if(expression.length() <= 10)
            {
                expression.setTextSize(35F)
            }
        }

        /***************************************************************************/
        // History
        /***************************************************************************/
        historic.setOnClickListener()
        {
            //Aqui va el codigo para crear el spinnerdialog
            val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()
            var mylist: ArrayList<String>? = loadHistory()

            // Comprobamos que la lista no es es null al cargala
            if (mylist != null)
            {
                var cont:Int = mylist.size -1

                while(cont >= 0)
                {
                    arraySpinnerModel.add(SpinnerModel(mylist[cont]))
                    cont--
                }
            }

            // Init single select Fragment
            val spinnerSingleSelectDialogFragment =
                SpinnerDialogFragment.newInstance(
                    SpinnerSelectionType.SINGLE_SELECTION,"History", arraySpinnerModel,
                    object :
                        OnSpinnerOKPressedListener {
                        override fun onSingleSelection(data: SpinnerModel, selectedPosition: Int)
                        {
                            if(!(data.text[0]=='='))
                            {
                                expression.setText(Html.fromHtml(paintstring(expression.text.toString() + data.text)))
                                tresult.setText("")
                            }
                            else if(data.text[0]=='-')
                            {
                                expression.setText(Html.fromHtml(paintstring("(" + expression.text.toString() + data.text + ")")))
                                tresult.setText("")
                            }
                            else
                            {
                                if(data.text[1]=='-')
                                {
                                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "(" + data.text.substring(1,data.text.length) + ")")))
                                }
                                else
                                {
                                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + data.text.substring(1,data.text.length))))
                                }
                                tresult.setText("")
                            }
                            checknumberchar()
                        }

                        override fun onMultiSelection(data: List<SpinnerModel>,selectedPosition: Int)
                        {/* It will never send Multi selection data in SINGLE_SELECTION Mode*/ }

                    }, 0
                )
            spinnerSingleSelectDialogFragment.showSearchBar = false
            spinnerSingleSelectDialogFragment.buttonText = "Load Data"
            spinnerSingleSelectDialogFragment.themeColorResId = resources.getColor(R.color.colorBase)
            spinnerSingleSelectDialogFragment.show(supportFragmentManager, "SpinnerDialogFragmentSingle")
        }

        /***************************************************************************/
        // Number
        /***************************************************************************/

        number0.setOnClickListener()
        {
            clear_expression()

            // condicines a tener en cuenta
            // 1. No se puede poner mas de un 0 a las izquierda.
            // 2. si es un numero decimal enconce se puede poner todos los Ceros que se necesiten

            var cont: Int = expression.text.length - 1
            var checkvalue: Boolean = false
            var controldecimal: Boolean = true

            if (expression.text.isNotEmpty())
            {
                var nueva = ""

                // si se encuentra algun signo y se presiona cero se agrega un cero
                if(expression.text[expression.text.length - 1] == '+' ||
                    expression.text[expression.text.length - 1] == '-' ||
                    expression.text[expression.text.length - 1] == '*' ||
                    expression.text[expression.text.length - 1] == '/')
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "0")))
                }

                // separamos el numero en una variable para evaluar por separado si se tienes que poner 0 o no
                while(cont >= 0)
                {
                    if (expression.text[cont].isDigit() || expression.text[cont] == '.')
                    {
                        nueva=nueva+expression.text[cont]
                    }
                    else
                    {
                        cont = 0
                    }
                    cont --
                }

                // si encontramos un punto al final de la expression entonce colocamos un cero al final
                for (item in nueva)
                {
                    if(item == '.')
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "0")))
                        controldecimal = false
                    }
                }

                // con esto podemos colocamos cero en la expression si no hay ceros a la izquierda
                for(item in nueva)
                {
                    if(controldecimal)
                    {
                        if(Character.getNumericValue(item.toInt()) > 0)
                        {
                            checkvalue = true
                        }
                    }
                }

                if (checkvalue)
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "0")))
                }
            }
            else
            {
                expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "0")))
            }
        }

        number1.setOnClickListener()
        {
            clear_expression()
            checknumberchar()
            checkzero('1')

            val preferences = getSharedPreferences("historydata", 0)
            preferences.edit().remove("historydata").commit()
        }

        number2.setOnClickListener()
        {
            clear_expression()
            checkzero('2')
        }

        number3.setOnClickListener()
        {
            clear_expression()
            checkzero('3')
        }

        number4.setOnClickListener()
        {
            clear_expression()
            checkzero('4')
        }

        number5.setOnClickListener()
        {
            clear_expression()
            checkzero('5')
        }

        number6.setOnClickListener()
        {
            clear_expression()
            checkzero('6')
        }

        number7.setOnClickListener()
        {
            clear_expression()
            checkzero('7')
        }

        number8.setOnClickListener()
        {
            clear_expression()
            checkzero('8')
        }

        number9.setOnClickListener()
        {
            clear_expression()
            checkzero('9')
        }

        /***************************************************************************/
        // math signs
        /***************************************************************************/

        numberplus.setOnClickListener()
        {
            ansData()
            if(expression.text.isNotEmpty())
            {
                // comprobamos que lo procede un simbolo entonce lo cambiamos por el simbolo que queremos cambiarlo
                if(expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/')
                {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-'))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1) + "+")))
                    }
                    else
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1))))
                    }
                }
                // si no tenemos ningun simbolo aritmetico entonce colocamos el simbolo + en este caso
                else
                {
                    if(!(expression.text[expression.text.length - 1] == '('))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "+")))
                    }
                }
            }
        }

        numberless.setOnClickListener()
        {
            ansData()
            if(expression.text.isNotEmpty())
            {
                // comprobamos que lo procede un simbolo entonce lo cambiamos por el simbolo que queremos cambiarlo
                if(expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/')
                {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-'))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1) + "-")))
                    }
                }
                // si no tenemos ningun simbolo aritmetico entonce colocamos el simbolo + en este caso
                else
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "-")))
                }
            }
        }

        numbermultiply.setOnClickListener()
        {
            ansData()
            if(expression.text.isNotEmpty())
            {
                // comprobamos que lo procede un simbolo entonce lo cambiamos por el simbolo que queremos cambiarlo
                if(expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/')
                {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-'))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1) + "*")))
                    }
                    else
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1))))
                    }
                }
                // si no tenemos ningun simbolo aritmetico entonce colocamos el simbolo + en este caso
                else
                {
                    if(!(expression.text[expression.text.length - 1] == '('))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "*")))
                    }
                }
            }
        }

        numberdivide.setOnClickListener()
        {
            ansData()
            if(expression.text.isNotEmpty())
            {
                // comprobamos que lo procede un simbolo entonce lo cambiamos por el simbolo que queremos cambiarlo
                if(expression.text[expression.text.length - 1] == '+' || expression.text[expression.text.length - 1] == '-' || expression.text[expression.text.length - 1] == '*' || expression.text[expression.text.length - 1] == '/')
                {
                    if (!(expression.text[expression.text.length - 2] == '(' && expression.text[expression.text.length - 1] == '-'))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.substring(0,expression.text.length - 1) + "/")))
                    }
                    else
                    {
                        expression.setText(expression.text.substring(0,expression.text.length - 1))
                    }
                }
                // si no tenemos ningun simbolo aritmetico entonce colocamos el simbolo / en este caso
                else
                {
                    if(!(expression.text[expression.text.length - 1] == '('))
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "/")))
                    }
                }
            }
        }

        numberpunto.setOnClickListener()
        {
            if(expression.text.isNotEmpty())
            {
                if(expression.text[expression.text.length - 1].isDigit())
                {
                    var run: Boolean = true
                    var estado: Int = 0
                    var cont: Int = expression.text.length-1

                    while (cont >= 0 && run)
                    {
                        if (expression.text[cont].isDigit())
                        {
                            cont--
                        }
                        else
                        {
                            // debo no apuntar nada por que ya es un numero decimal
                            if (expression.text[cont] == '.')
                            {
                                run = false
                                // significa que se ha encontrado un punto no que escribira otro punto
                                estado = 1
                            }
                            // debo apuntar un punto por que es un numero sin ninguna ,
                            else if (expression.text[cont] == '(' ||
                                     expression.text[cont] == ')' ||
                                     expression.text[cont] == '+' ||
                                     expression.text[cont] == '-' ||
                                     expression.text[cont] == '*' ||
                                     expression.text[cont] == '/' ||
                                     expression.text[cont] == '%')
                            {
                                run = false
                                // sinifica que se ha encontrado un signo se escribira un punto
                                estado = 2
                            }
                        }
                    }

                    // en este momento escribimos el puntos si fuera necesario
                    if (estado == 0 || estado == 2)
                    {
                        expression.setText(Html.fromHtml(paintstring(expression.text.toString() + ".")))
                    }
                    // si intentas poner dos veces el pinto se mostrara un mensaje indicando que no es valido
                    else if (estado == 1)
                    {
                        val toast = Toast.makeText(applicationContext, "Invalid format used.", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
                else if(expression.text[expression.text.length - 1] == ')')
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "*0.")))
                }
                else if(!(expression.text[expression.text.length - 1] == '.'))
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "0.")))
                }
            }
            else
            {
                expression.setText("0.")
            }
        }

        numberpercentage.setOnClickListener()
        {
            if(expression.text.isNotEmpty())
            {
                if(!(expression.text[expression.text.length - 1] == '+'||
                    expression.text[expression.text.length - 1] == '-'||
                    expression.text[expression.text.length - 1] == '*'||
                    expression.text[expression.text.length - 1] == '/'||
                    expression.text[expression.text.length - 1] == '%'||
                    expression.text[expression.text.length - 1] == '('))
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "%")))
                }
                else
                {
                    val toast = Toast.makeText(applicationContext, "Invalid format used.", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }

        numberplusless.setOnClickListener()
        {
            //Si la expresion no esta vacia
            if (expression.text.isNotEmpty())
            {
                //si pulsamos un parentecis y ya exite otro por ejemplo (2+2) y precionamos este boton pasaria a "(2+2)*(-"
                if(expression.text[expression.text.length - 1] == ')')
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "*(-")))
                }
                //si intenta poner el (- pero antes estas algun simbolo como + - * / entonce simplemente agregamos '(-'
                else if((expression.text[expression.text.length - 1] == '+' ||
                         expression.text[expression.text.length - 1] == '-' ||
                         expression.text[expression.text.length - 1] == '*' ||
                         expression.text[expression.text.length - 1] == '/') &&
                         !(expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "(-")))
                }
                //Si solo tenemos dos caracteres o mas y ademas los dos ultimos caracteres son (- los eliminamos
                else if (expression.text.length >= 2 && (expression.text[expression.text.length - 1] == '-' && expression.text[expression.text.length - 2] == '('))
                {
                    var reststring = expression.text.substring(0, expression.text.length - 2)
                    expression.setText(Html.fromHtml(paintstring("$reststring")))
                }
                else
                {
                    var numero: String = ""
                    var cont: Int = expression.text.length - 1
                    var finish: Boolean = true

                    // Con este while consigo el numero que termina la cadena de la exprecion
                    while (finish)
                    {
                        if (!(cont<0))
                        {
                            if (expression.text[cont].isDigit() || expression.text[cont] == '.')
                            {
                                numero = numero + expression.text[cont].toString()
                                cont--
                            }
                            else
                            {
                                finish = false
                            }
                        }
                        else
                        {
                            finish = false
                        }
                    }
                    numero = numero.reversed()

                    //Creo una cadena con la parte de la exprecion que quiero mantener
                    var reststring = expression.text.substring(0, expression.text.length - numero.length)

                    //con este if compruebo que la parte de la exprecion es mas grande que el numero por que si fueran igual solo tendria que ponerlo en negativo
                    if (expression.text.length > numero.length)
                    {
                        if(expression.text[(expression.text.length - numero.length) - 1] == '-' && expression.text[(expression.text.length - numero.length) - 2] == '(')
                        {
                            expression.setText(Html.fromHtml(paintstring("${reststring.substring(0,reststring.length - 2)}${numero}")))
                        }
                        else if(expression.text.length - numero.length -1 >= 0)
                        {
                            //Si el numero esta al lado de alguna simbolo aritmetico entonce para ponerlo en negativo agrego (- "+numero"
                            if((expression.text[(expression.text.length - numero.length) -1 ] == '+' ||
                                        expression.text[(expression.text.length - numero.length) -1 ] == '-' ||
                                        expression.text[(expression.text.length - numero.length) -1 ] == '*'||
                                        expression.text[(expression.text.length - numero.length) -1 ] == '/') &&
                                expression.text[(expression.text.length - numero.length) -2 ] != '(')
                            {
                                expression.setText(Html.fromHtml(paintstring("${reststring.substring(0,reststring.length) }(-${numero}")))
                            }
                        }
                    }
                    //Si el numero es igual que la exprecion simplemete lo paso a negativo
                    else
                    {
                        expression.setText(Html.fromHtml(paintstring("(-${numero}")))
                    }
                }
            }
            //Si la expresion esta vacia pues colocamos el (- para eniciar una exprecion con un numero negativo
            else
            {
                expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "(-")))
            }
        }

        numberopenparenthesis.setOnClickListener()
        {
            if (expression.text.isNotEmpty())
            {
                if(expression.text[expression.text.length - 1] == ')' || expression.text[expression.text.length - 1].isDigit())
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "*(")))
                }
                else
                {
                    expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "(")))
                }
            }
            else
            {
                expression.setText(Html.fromHtml(paintstring(expression.text.toString() + "(")))
            }
        }

        numbercloseparenthesis.setOnClickListener()
        {
            expression.setText(Html.fromHtml(paintstring(expression.text.toString() + ")")))
        }

        /***************************************************************************/
        // clear field
        /***************************************************************************/

        numberac.setOnClickListener()
        {
            expression.setText("")
            tresult.setText("")
            checksizeoftext()
        }

        numbrebackspace.setOnClickListener()
        {
            if(expression.text.length>=1)
            {
                expression.setText(Html.fromHtml(paintstring(expression.text.substring(0, expression.text.length - 1))))
            }
            checksizeoftext()
        }

        /***************************************************************************/
        // Result expression
        /***************************************************************************/

        numberresult.setOnClickListener()
        {

            if (expression.text.contains('%'))
            {
                var i: Int = expression.text.length -1
                var number: String = ""
                var dataResult: String = ""

                while(0 <= i)
                {
                    if(expression.text[i] == '%')
                    {
                        var index = i - 1

                        while(0 <= index)
                        {
                            if(index >= 0 )
                            {
                                if(expression.text[index].isDigit() || expression.text[index] == '.' )
                                {
                                    number = number + expression.text[index]
                                }
                                else
                                {
                                    index = -1
                                }
                            }
                            index--
                        }
                        if (number.isNotEmpty())
                        {
                            dataResult = "(${number.toString().reversed()}/100)" + dataResult
                        }
                        else
                        {
                            dataResult = "${number.toString().reversed()}/100" + dataResult
                        }

                        i = i - number.length
                        number=""
                    }
                    else
                    {
                        dataResult = expression.text[i].toString() + dataResult
                    }
                    i--
                }

                if (checkParentesis(dataResult))
                {
                    val format = DecimalFormat()
                    format.setMaximumFractionDigits(4)

                    tresult.setText(check_integer(format.format(Calculator_functions().basicEquations(dataResult))))
                    state = true

                    //save history
                    saveHistory(expression.text.toString())
                    saveHistory("="+tresult.text.toString())
                }
                else
                {
                    val toast = Toast.makeText(applicationContext, "Invalid format used.", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
            else
            {
                if(expression.text.isNotEmpty())
                {
                    var checkExp = expression.text.toString()

                    if (checkParentesis(checkExp))
                    {
                        val format = DecimalFormat()
                        format.setMaximumFractionDigits(4)

                        tresult.setText(check_integer(format.format(Calculator_functions().basicEquations(checkExp))))
                        state = true

                        //save history
                        saveHistory(expression.text.toString())
                        saveHistory("="+tresult.text.toString())
                    }
                    else
                    {
                        val toast = Toast.makeText(applicationContext, "Invalid format used.", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            }
        }
    }
}