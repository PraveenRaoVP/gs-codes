package exercism;

class TimFromMarketing {
    public String print(Integer id, String name, String department) {
        if(id==null) {
            if(department!=null)
                return name+" - "+department.toUpperCase();
            return name+ " - "+"OWNER";
        } else if(department == null) {
            return "["+id+"]"+" - "+name+" - "+"OWNER";
        } else {
            return "["+id+"]"+" - "+name+" - "+department.toUpperCase();
        }
    }
}
