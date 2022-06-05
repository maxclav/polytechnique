using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerHealth : MonoBehaviour {

    [SerializeField]
    private bool vieEstEnPourcentage;

    AudioSource _audioSource { get; set; }

    [SerializeField]
    private float maxHealth;
    private float currentHealth { get; set; }
    private bool isDead { get; set; }
    private float healthPointsRatio { get; set; }
    
    public Slider healthPointsSlider;
    public Text healthPointsRatioText;
    public Text gameOverText;

    private bool recoitSoin { get; set; }
    private bool recoitDegat { get; set; }

    private void Awake()
    {
        gameOverText.gameObject.SetActive(false);
    }

    void Start ()
    {
        vieEstEnPourcentage = true;
        maxHealth = 100f;
        CalculateHealthRatio();
        RevivePlayer();
    }
	
	void Update ()
    {
        CalculateHealthRatio();

        if ((int)(healthPointsRatio * 100) == 0)
        {
            KillPlayer(); // Sometimes, player can jump at 0,x health
        }
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
            CalculateHealthRatio();
        }
    }

    void DamagePlayer(float damageValue)
    {
        if (isDead == false)
        {
            powerUpsInput powerup = this.gameObject.GetComponent<powerUpsInput>();
            if (powerup == null)
            {
                recoitDegat = true;
                if ((currentHealth - damageValue) <= 0)
                {
                    KillPlayer();
                }
                else if (currentHealth <= maxHealth)
                {
                    currentHealth -= damageValue;
                }
                CalculateHealthRatio();
            }
            else if (!powerup.shield)
            {
                recoitDegat = true;
                if ((currentHealth - damageValue) <= 0)
                {
                    KillPlayer();
                }
                else if (currentHealth <= maxHealth)
                {
                    currentHealth -= damageValue;
                }
                CalculateHealthRatio();
            }
        }
    }

    void CalculateHealthRatio()
    {
        healthPointsRatio = (currentHealth / maxHealth);
        healthPointsSlider.value = healthPointsRatio;
        if(!isDead)
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
        else
        {
            healthPointsRatioText.text = "Mort";
        }
    }

    void KillPlayer()
    {
        currentHealth = 0;
        isDead = true;
        CalculateHealthRatio(); // Suppose to be 0%
        gameObject.SendMessage("GameOver");
        gameOverText.gameObject.SetActive(true);
    }
    void RevivePlayer()
    {
        currentHealth = maxHealth;
        isDead = false;
        CalculateHealthRatio(); // Suppose to be 100%
        gameOverText.gameObject.SetActive(false);
    }
}
