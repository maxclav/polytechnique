using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Munitions : MonoBehaviour
{
    public Text cooldDownTimer;

    [SerializeField]
    float timerReloadBullets = 4f;
    private float timerReloadBulletsRemaining;

    //[SerializeField]
    private int nombreMunitionsMaximal = 5;
    private int nombreMunitionActuel { get; set; }
    private bool gunIsEmpty = false;
    
    private Color32 colorMunition;

    public Image HUDmunition1;
    public Image HUDmunition2;
    public Image HUDmunition3;
    public Image HUDmunition4;
    public Image HUDmunition5;

    // Use this for initialization
    void Start ()
    {
        timerReloadBulletsRemaining = timerReloadBullets + 1.00F /*+ 0.99F*/;

        colorMunition = HUDmunition1.GetComponent<Image>().color;
        
        nombreMunitionActuel = nombreMunitionsMaximal;
    }
	
	// Update is called once per frame
	void Update ()
    {
        if (gunIsEmpty)
        {
            cooldDownTimer.gameObject.SetActive(true);
            timerReloadBulletsRemaining -= Time.deltaTime;
            cooldDownTimer.text = "Recharge: " + ((int)timerReloadBulletsRemaining).ToString();

            if (timerReloadBulletsRemaining <= 1)
            {
                ReloadGun();
            }
        }
        else if (!gunIsEmpty)
        {
            cooldDownTimer.gameObject.SetActive(false);
        }
    }

    public void PerdreBalle()
    {
        if (nombreMunitionActuel == 5)
        {
            HUDmunition5.GetComponent<Image>().color = new Color32(0, 0, 0, 255);
        }
        else if (nombreMunitionActuel == 4)
        {
            HUDmunition4.GetComponent<Image>().color = new Color32(0, 0, 0, 255);
        }
        else if (nombreMunitionActuel == 3)
        {
            HUDmunition3.GetComponent<Image>().color = new Color32(0, 0, 0, 255);
        }
        else if (nombreMunitionActuel == 2)
        {
            HUDmunition2.GetComponent<Image>().color = new Color32(0, 0, 0, 255);
        }
        else if (nombreMunitionActuel == 1)
        {
            HUDmunition1.GetComponent<Image>().color = new Color32(0, 0, 0, 255);
            gunIsEmpty = true;
            gameObject.SendMessage("BlockGun");
            //reloadFusil();
        }
        else if (nombreMunitionActuel == 0)
        {
            // Bruit aucune balle
        }
        else
        {
            // Erreur
        }
        nombreMunitionActuel -= 1;
    }

    void ReloadGun()
    {
        gameObject.SendMessage("UnblockGun");
        gunIsEmpty = false;
        nombreMunitionActuel = nombreMunitionsMaximal; // 5
        RefreshBulletNumber();
        timerReloadBulletsRemaining = timerReloadBullets + 1.00F /*+ 0.99F*/;
    }    

    void SalvageBullet(GameObject bullet)
    {
        if (gunIsEmpty)
        {
            gameObject.SendMessage("UnblockGun");
            gunIsEmpty = false;
        }
        if (nombreMunitionActuel < nombreMunitionsMaximal) 
        {
            nombreMunitionActuel++;
            DestroyObject(bullet);
        }
        RefreshBulletNumber();
    }

    void RefreshBulletNumber()
    {
        timerReloadBulletsRemaining = timerReloadBullets + 1.00F /*+ 0.99F*/;
        switch (nombreMunitionActuel)
        {
            case 5:
                HUDmunition5.GetComponent<Image>().color = colorMunition;
                goto case 4;
            case 4:
                HUDmunition4.GetComponent<Image>().color = colorMunition;
                goto case 3;
            case 3:
                HUDmunition3.GetComponent<Image>().color = colorMunition;
                goto case 2;
            case 2:
                HUDmunition2.GetComponent<Image>().color = colorMunition;
                goto case 1;
            case 1:
                HUDmunition1.GetComponent<Image>().color = colorMunition;
                break;
            default:
                return;
        }
    }
}
