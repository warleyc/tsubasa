/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageRewardDetailComponent } from 'app/entities/m-ticket-quest-stage-reward/m-ticket-quest-stage-reward-detail.component';
import { MTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MTicketQuestStageReward Management Detail Component', () => {
    let comp: MTicketQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MTicketQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mTicketQuestStageReward: new MTicketQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTicketQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTicketQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
