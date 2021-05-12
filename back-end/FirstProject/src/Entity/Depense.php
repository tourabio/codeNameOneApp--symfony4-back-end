<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Depense
 *
 * @ORM\Table(name="depense", indexes={@ORM\Index(name="idUtilisateur", columns={"idUtilisateur"}), @ORM\Index(name="id_finance", columns={"id_finance"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\DepenseRepository")
 */
class Depense
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var float
     *
     * @ORM\Column(name="valeur", type="float", precision=10, scale=0, nullable=false)
     */
    private $valeur;

    /**
     * @var string
     *
     * @ORM\Column(name="source", type="string", length=255, nullable=false)
     */
    private $source;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="justificatif", type="string", length=255, nullable=false)
     */
    private $justificatif;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUtilisateur", referencedColumnName="ID_User")
     * })
     */
    private $idutilisateur;

    /**
     * @var \Fichefinance
     *
     * @ORM\ManyToOne(targetEntity="Fichefinance")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_finance", referencedColumnName="id")
     * })
     */
    private $idFinance;




    private $dateFormatted;

    public function getDateFormatted(): ? string
    {
        return $this->dateFormatted;
    }

    public function setDateFormatted(string $dateFormatted): self
    {
        $this->dateFormatted = $dateFormatted;

        return $this;
    }






    public function getId(): ?string
    {
        return $this->id;
    }

    public function getValeur(): ?float
    {
        return $this->valeur;
    }

    public function setValeur(float $valeur): self
    {
        $this->valeur = $valeur;

        return $this;
    }

    public function getSource(): ?string
    {
        return $this->source;
    }

    public function setSource(string $source): self
    {
        $this->source = $source;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getJustificatif(): ?string
    {
        return $this->justificatif;
    }

    public function setJustificatif(string $justificatif): self
    {
        $this->justificatif = $justificatif;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getIdutilisateur(): ?User
    {
        return $this->idutilisateur;
    }

    public function setIdutilisateur(?User $idutilisateur): self
    {
        $this->idutilisateur = $idutilisateur;

        return $this;
    }

    public function getIdFinance(): ?Fichefinance
    {
        return $this->idFinance;
    }

    public function setIdFinance(?Fichefinance $idFinance): self
    {
        $this->idFinance = $idFinance;

        return $this;
    }



}
