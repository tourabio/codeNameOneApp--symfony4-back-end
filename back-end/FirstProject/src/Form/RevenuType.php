<?php

namespace App\Form;

use App\Entity\Revenu;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RevenuType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('valeur')
            ->add('source')
            ->add('description')
            ->add('justificatif')
            ->add('date')
            ->add('idutilisateur')
            ->add('idFinance')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Revenu::class,
        ]);
    }
}
