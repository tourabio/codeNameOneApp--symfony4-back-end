<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={@ORM\UniqueConstraint(name="Email", columns={"Email"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")
 */
class User
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_User", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idUser;

    /**
     * @var string
     *
     * @ORM\Column(name="Name", type="string", length=30, nullable=false)
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="FamilyName", type="string", length=30, nullable=false)
     */
    private $familyname;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=10, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="Email", type="string", length=40, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="Password", type="string", length=20, nullable=false)
     */
    private $password;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Subject", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $subject = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="Class", type="string", length=10, nullable=true, options={"default"="NULL"})
     */
    private $class = 'NULL';

    public function getIdUser(): ?string
    {
        return $this->idUser;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getFamilyname(): ?string
    {
        return $this->familyname;
    }

    public function setFamilyname(string $familyname): self
    {
        $this->familyname = $familyname;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getSubject(): ?string
    {
        return $this->subject;
    }

    public function setSubject(?string $subject): self
    {
        $this->subject = $subject;

        return $this;
    }

    public function getClass(): ?string
    {
        return $this->class;
    }

    public function setClass(?string $class): self
    {
        $this->class = $class;

        return $this;
    }

    public function __toString()
    {
        return $this->getName();
    }


}
