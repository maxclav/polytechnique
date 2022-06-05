using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerHealth : MonoBehaviour {

    [SerializeField]
    private bool vieEstEnPourcentage;

    AudioSource _audioSource { get; set; }

    public float maxHealth;
    public float currentHealth;
    public bool showHealthText = true;

    private bool isDead { get; set; }
    private float healthPointsRatio { get; set; }
    
    public Slider healthPointsSlider;
    public Text healthPointsRatioText;
    public Text gameOverText;

    private bool recoitSoin { get; set; }
    private bool recoitDegat { get; set; }

    PlayerSounds _PS = null;

    private void Awake()
    {
        //gameOverText.gameObject.SetActive(false);
        _PS = GetComponent<PlayerSounds>();
    }

    void Start ()
    {
       // CalculateHealthRatio();
        RevivePlayer();
    }
	
	void Update ()
    {
        //CalculateHealthRatio();

        /*if ((int)(healthPointsRatio * 100) == 0)
        {
            KillPlayer(); // Sometimes, player can jump at 0,x health
        }*/
        recoitSoin = false;
        recoitDegat = false;
    }

    void HealPlayer(float healingValue)
    {
        if (currentHealth < maxHealth)
        {
            if (isDead == false && currentHealth != maxHealth)
            {
                recoitSoin = true;
                if ((currentHealth + healingValue) > maxHealth)
                {
                    currentHealth = maxHealth;
                }
                else
                {
                    currentHealth += healingValue;
                }
            }
            //CalculateHealthRatio();
        }
    }

    void DamagePlayer(float damageValue)
    {
        if (!isDead)
        {
            //powerUpsInput powerup = this.gameObject.GetComponent<powerUpsInput>();
            PlayerInventoryScript  inventory = this.gameObject.GetComponent<PlayerInventoryScript>();
			if (!inventory.shieldActive && !inventory.invincible)
            {
                recoitDegat = true;
                _PS.PlayHurt();
                if ((currentHealth - damageValue) <= 0)
                {
                    KillPlayer();
                }
                else
                {
                    currentHealth -= damageValue;
                }
                //CalculateHealthRatio();
            }
        }
    }

    /*void CalculateHealthRatio()
    {
        healthPointsRatio = (currentHealth / maxHealth);
        healthPointsSlider.value = healthPointsRatio;
        if(!isDead)
        {
            if (healthPointsRatio < 1 && healthPointsRatio > 0)
            {
                if (vieEstEnPourcentage)
                {
                    healthPointsRatioText.text = ((int)(healthPointsRatio * 100)).ToString() + " %";
                }
                else
                {
                    healthPointsRatioText.text = ((int)currentHealth).ToString() + "/" + ((int)maxHealth).ToString();
                }
            }
        }
        else
        {
            healthPointsRatioText.text = "Mort";
        }
    }*/

    void KillPlayer()
    {
        // _PS.PlayDeath(); // ca glitch en criss
        currentHealth = 0;
        isDead = true;
        //CalculateHealthRatio(); // Suppose to be 0%
        gameObject.SendMessage("GameOver");
       //ameOverText.gameObject.SetActive(true);
    }
    void RevivePlayer()
    {
        currentHealth = maxHealth;
        isDead = false;
        //CalculateHealthRatio(); // Suppose to be 100%
        //gameOverText.gameObject.SetActive(false);
    }
}
