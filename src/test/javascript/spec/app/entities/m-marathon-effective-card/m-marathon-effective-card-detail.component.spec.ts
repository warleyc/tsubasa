/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonEffectiveCardDetailComponent } from 'app/entities/m-marathon-effective-card/m-marathon-effective-card-detail.component';
import { MMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';

describe('Component Tests', () => {
  describe('MMarathonEffectiveCard Management Detail Component', () => {
    let comp: MMarathonEffectiveCardDetailComponent;
    let fixture: ComponentFixture<MMarathonEffectiveCardDetailComponent>;
    const route = ({ data: of({ mMarathonEffectiveCard: new MMarathonEffectiveCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonEffectiveCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonEffectiveCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonEffectiveCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonEffectiveCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
