package com.prom.eazy;

public class BonPointageItem {
        private int id;
        private int mImageResource;
        private String txtVendeur;
        private String txtVehicule;
        private String txtSecteur;

    public String getTxtType() {
        return txtType;
    }

    private String txtType;

        public BonPointageItem(int id, int imageResource, String txtVendeur, String txtVehicule, String txtSecteur, String txtType) {
            this.id=id;
            this.txtVendeur = txtVendeur;
            this.txtVehicule = txtVehicule;
            this.txtSecteur = txtSecteur;
            this.mImageResource = imageResource;
            this.txtType = txtType;
        }
        public String getTxtVendeur() {
            return this.txtVendeur;
        }
        public String getTxtVehicule() {
            return this.txtVehicule;
        }
        public String getTxtSecteur() {
            return this.txtSecteur;
        }
        public int getId() {
            return this.id;
        }
        public int getImageResource() {
            return mImageResource;
        }

    }
