package me.ymir.vega.entites.storage;
import me.ymir.vega.entites.Items.Pickaxe;
import me.ymir.vega.entites.Items.ResourceItem;
import java.util.HashMap;



public class ItemStorage {

    protected static HashMap<Integer, Pickaxe> PICKAXE = new HashMap<>();
    protected final static HashMap<Integer, ResourceItem> RESOURCE_ITEMS = new HashMap<>();
    protected final static HashMap<String,Integer> STRING_TO_ID = new HashMap<>();



    private static void addResource(ResourceItem resourceItem){
        RESOURCE_ITEMS.put(resourceItem.getId(),resourceItem);
        STRING_TO_ID.put(resourceItem.getName().replace(" ","_").toLowerCase(),resourceItem.getId());
    }


    public static HashMap<Integer, ResourceItem> getResources(){return RESOURCE_ITEMS;}

    public static ResourceItem getResource(int id,String name){
        if(id==0&&STRING_TO_ID.containsKey(name.toLowerCase())){
            return RESOURCE_ITEMS.get(STRING_TO_ID.get(name.toLowerCase().trim()));
        }
        return RESOURCE_ITEMS.get(id);
    }


    public static Integer getSellPrice(int id){
        return getResource(id,"").getSellPrice();
    }


    public static String itemInfo(int id,String name){
        StringBuilder string = new StringBuilder();
        string.append("**Ad:** %s %s\n").append("**ID:** %d \n").append("**Açıklama:** %s \n");

        if(RESOURCE_ITEMS.containsKey(id)|| STRING_TO_ID.containsKey(name)){
            ResourceItem item = getResource(id,name);
            string.append("**Satış Fiyatı:** %s \n").append("**Alış Fiyatı:** %s \n").append("**Seviye:** %d \n").append("**Kazıda Bulma Oranı:** %s \n").append("**Üretim Kaynağı:** %s \n").append("**Eritildiğinde:** %s \n");
            return String.format(string.toString(),item.getName(),item.getEmoji(),item.getId(),item.getDesc(),item.getSellPrice() != -1 ? String.valueOf(item.getSellPrice()) : "Satılamaz",
                    item.getBuyPrice() != -1 ? String.valueOf(item.getBuyPrice()) : "Alınamaz",
                    item.getRequiredLevel(),item.getChance() !=0 ? String.valueOf(item.getChance()) : "Bulunamaz", item.isCanUseCraft() ? "Olabilir" : "Olamaz",
                    item.getMelted() != null ? item.getMelted().getName() : "Eritilemez");
        }else if(PICKAXE.containsKey(id)){
            Pickaxe pickaxe = PICKAXE.get(id);
            ResourceItem resource = RESOURCE_ITEMS.get(pickaxe.getResource());
            string.append("**Canı:** %s \n").append("**Kaynak:** %s \n").append("**Hızı:** %d Saniye \n").append("**Şans Yüzdesi:** %d \n");
            return String.format(string.toString(),pickaxe.getName(),pickaxe.getEmoji(),pickaxe.getId(),pickaxe.getDesc(),pickaxe.getDurability()+"/"+pickaxe.getMaxDurability(),
                    resource.getEmoji()+resource.getName(),pickaxe.getSpeed(),pickaxe.getChance());

        }else{
            return "Bulunamadı!";
        }
    }




    public static HashMap<Integer, Pickaxe> getPickaxes() {
        return PICKAXE;
    }

    public static void addPickaxe(Pickaxe pickaxe){
        while (true){
            if(PICKAXE.containsKey(pickaxe.getId())){
                pickaxe.setId(pickaxe.getId()+1);
            }else{
                PICKAXE.put(pickaxe.getId(),pickaxe);
                break;
            }
        }
    }

    public static void removePickaxe(Pickaxe pickaxe){PICKAXE.remove(pickaxe.getId());}






    public static void updateList(){
        addResource(new ResourceItem(0,"Çöp","Çöp eşya","<:Garbage:838793671364706354>",-1,0,0,0,false,null));

        addResource(new ResourceItem(2,"Taş","Isıtılmış Kırıktaş","<:stone:838177515931303956>",5,2,0,0,false,getResource(0,"")));
        addResource(new ResourceItem(1,"Kırık Taş","Kırıktaş","<:Cobblestone:838792579309895723>",5,1,0,15,true,getResource(2,"")));

        addResource(new ResourceItem(3,"Kömür","Kömür","<:coal:838171080144978001>",100,1,10,1,false,getResource(0,"")));

        addResource(new ResourceItem(5,"Demir","Demir Külçesi","<:iron:838170834845564958>",-1,10,1,0,true,getResource(0,"")));
        addResource(new ResourceItem(4,"Demir Cevheri","Demir Cevheri","<:IronOre:838792882982355004>",50,5,20,6,false,getResource(5,"")));

        addResource(new ResourceItem(7,"Altın","Altın Külçesi","<:gold:838075113348792371>",-1,50,2,0,true,getResource(0,"")));
        addResource(new ResourceItem(6,"Altın Cevheri","Altın Cevheri","<:GoldOre:838793116449636353>",500,25,40,5,false,getResource(7,"")));

        addResource(new ResourceItem(9,"Elmas","Elmas Külçesi","<:elmas:838074743667818496>",-1,75,3,0,true,getResource(0,"")));
        addResource(new ResourceItem(8,"Elmas Cevheri","Elmas Cevheri","<:DiamondOre:838793309861969991>",100,50,60,5,false,getResource(9,"")));

        addResource(new ResourceItem(10,"Zümrüt","Zümrüt","<:zmrt:838074323394625546>",-1,-1,100,1,false,getResource(0,"")));
    }





}
