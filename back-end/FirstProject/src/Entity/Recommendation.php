<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Recommendation
 *
 * @ORM\Table(name="recommendation", indexes={@ORM\Index(name="idUtilisateur", columns={"idUtilisateur"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\RecommendationRepository")
 */
class Recommendation
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
     * @var string
     *
     * @ORM\Column(name="sujet", type="string", length=50, nullable=false)
     */
    private $sujet;

    /**
     * @var string
     *
     * @ORM\Column(name="comment", type="string", length=500, nullable=false)
     */
    private $comment;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=30, nullable=false)
     */
    private $etat;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUtilisateur", referencedColumnName="ID_User")
     * })
     */
    private $idutilisateur;





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

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function getComment(): ?string
    {
        return $this->comment;
    }

    public function setComment(string $comment): self
    {
        $this->comment = $comment;

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

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

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


}
