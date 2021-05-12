<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Examen
 *
 * @ORM\Table(name="examen", indexes={@ORM\Index(name="id_matiere", columns={"id_matiere"})})
 * @ORM\Entity
 */
class Examen
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_examen", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idExamen;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_matiere", type="string", length=200, nullable=false)
     */
    private $nomMatiere;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_ex", type="date", nullable=false)
     */
    private $dateEx;

    /**
     * @var string
     *
     * @ORM\Column(name="duree_ex", type="string", length=11, nullable=false)
     */
    private $dureeEx;

    /**
     * @var string
     *
     * @ORM\Column(name="PDF", type="string", length=200, nullable=false)
     */
    private $pdf;

    /**
     * @var int
     *
     * @ORM\Column(name="inscription", type="integer", nullable=false)
     */
    private $inscription;

    /**
     * @var \Matiere
     *
     * @ORM\ManyToOne(targetEntity="Matiere")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_matiere", referencedColumnName="id_matiere")
     * })
     */
    private $idMatiere;

    public function getIdExamen(): ?int
    {
        return $this->idExamen;
    }

    public function getNomMatiere(): ?string
    {
        return $this->nomMatiere;
    }

    public function setNomMatiere(string $nomMatiere): self
    {
        $this->nomMatiere = $nomMatiere;

        return $this;
    }

    public function getDateEx(): ?\DateTimeInterface
    {
        return $this->dateEx;
    }

    public function setDateEx(\DateTimeInterface $dateEx): self
    {
        $this->dateEx = $dateEx;

        return $this;
    }

    public function getDureeEx(): ?string
    {
        return $this->dureeEx;
    }

    public function setDureeEx(string $dureeEx): self
    {
        $this->dureeEx = $dureeEx;

        return $this;
    }

    public function getPdf(): ?string
    {
        return $this->pdf;
    }

    public function setPdf(string $pdf): self
    {
        $this->pdf = $pdf;

        return $this;
    }

    public function getInscription(): ?int
    {
        return $this->inscription;
    }

    public function setInscription(int $inscription): self
    {
        $this->inscription = $inscription;

        return $this;
    }

    public function getIdMatiere(): ?Matiere
    {
        return $this->idMatiere;
    }

    public function setIdMatiere(?Matiere $idMatiere): self
    {
        $this->idMatiere = $idMatiere;

        return $this;
    }


}
