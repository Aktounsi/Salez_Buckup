package com.prom.eazy;

public class BonPointageItemModelObject extends ListObject {



    public BonPointageItemModel getBonPointageItemModel() {
        return bonPointageItemModel;
    }

    public void setBonPointageItemModel(BonPointageItemModel bonPointageItemModel) {
        this.bonPointageItemModel = bonPointageItemModel;
    }

    private BonPointageItemModel bonPointageItemModel;


        @Override
        public int getType() {
            if (this.bonPointageItemModel.getType_bp().equalsIgnoreCase("sortie")) {
                return TYPE_GENERAL_SORTIE;
            } else
                return TYPE_GENERAL_RETOUR;
        }
    }
