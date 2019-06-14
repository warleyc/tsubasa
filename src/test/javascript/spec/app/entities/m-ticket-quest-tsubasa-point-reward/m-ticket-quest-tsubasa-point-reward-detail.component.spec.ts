/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestTsubasaPointRewardDetailComponent } from 'app/entities/m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward-detail.component';
import { MTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MTicketQuestTsubasaPointReward Management Detail Component', () => {
    let comp: MTicketQuestTsubasaPointRewardDetailComponent;
    let fixture: ComponentFixture<MTicketQuestTsubasaPointRewardDetailComponent>;
    const route = ({ data: of({ mTicketQuestTsubasaPointReward: new MTicketQuestTsubasaPointReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestTsubasaPointRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTicketQuestTsubasaPointRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestTsubasaPointRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTicketQuestTsubasaPointReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
