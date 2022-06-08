## CSV_and_XML_to_JSON
В этом задании отрабатывались навыки работы с файлами CSV, XML, JSON.
</br></br>

Для парсинга данных из **CSV** реализуем собственный метод *parseCSV()*, который использует **CSVReader** и созданную нами **ColumnPositionMappingStrategy**. 
```
public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))){
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> builder = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            list = builder.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
```

</br> Чтобы преобразовать получившийся список сотрудников в файл формата **JSON** воспользуемся средствами библиотеки **Gson** 
```
 public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }
```


</br> Для парсинга данных из **XML** реализуем метод, который пройдется по всем узлам (*Node*) файла XML и вытащит данные (*element.getTextContent()*) для создания экземпляров класса *Employee* 
 ```
        public static List<Employee> parseXML(String filename) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filename);
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String string = element.getTextContent();
                    Employee employee = Employee.makeEmployeeFromString(string);
                    employeeList.add(employee);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace(System.out);
        }
        return employeeList;
    }
```    
