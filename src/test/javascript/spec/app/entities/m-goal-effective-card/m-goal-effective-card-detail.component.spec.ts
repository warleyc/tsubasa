/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGoalEffectiveCardDetailComponent } from 'app/entities/m-goal-effective-card/m-goal-effective-card-detail.component';
import { MGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';

describe('Component Tests', () => {
  describe('MGoalEffectiveCard Management Detail Component', () => {
    let comp: MGoalEffectiveCardDetailComponent;
    let fixture: ComponentFixture<MGoalEffectiveCardDetailComponent>;
    const route = ({ data: of({ mGoalEffectiveCard: new MGoalEffectiveCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGoalEffectiveCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGoalEffectiveCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGoalEffectiveCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGoalEffectiveCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
