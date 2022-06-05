using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Munitions : MonoBehaviour
{

    public Text TimeInfoText;
    //[SerializeField]
    private int nombreMunitionsMaximal = 5;
    private int nombreMunitionActuel { get; set; }
    private bool gunIsEmpty = false;

    [SerializeField]
    float timerReloadBullets = 4f;
    private float timerReloadBulletsRemaining;

    public Image HUDmunition1;
    public Image HUDmunition2;
    public Image HUDmunition3;
    public Image HUDmunition4;
    public Image HUDmunition5;

    // Use this for initialization
    void Start ()
    {
        timerReloadBulletsRemaining = timerReloadBullets + 0.99F;
        nombreMunitionActuel = nombreMunitionsMaximal;
    }
	
	// Update is called once per frame
	void Update ()
    {
        if (gunIsEmpty)
        {
            TimeInfoText.gameObject.SetActive(true);
            timerReloadBulletsRemaining -= Time.deltaTime;
            TimeInfoText.text = ((int)timerReloadBulletsRemaining).ToString();

            if (timerReloadBulletsRemaining <= 1)
            {
                ReloadGun();
            }
        }
        else if (!gunIsEmpty)
        {
            TimeInfoText.gameObject.SetActive(false);
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
        HUDmunition1.GetComponent<Image>().color = new Color32(253, 141, 0, 255);
        HUDmunition2.GetComponent<Image>().color = new Color32(253, 141, 0, 255);
        HUDmunition3.GetComponent<Image>().color = new Color32(253, 141, 0, 255);
        HUDmunition4.GetComponent<Image>().color = new Color32(253, 141, 0, 255);
        HUDmunition5.GetComponent<Image>().color = new Color32(253, 141, 0, 255);
        nombreMunitionActuel = nombreMunitionsMaximal; // 5
        timerReloadBulletsRemaining = timerReloadBullets + 0.99F;
    }
}
