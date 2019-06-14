/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestSpecialRewardDetailComponent } from 'app/entities/m-quest-special-reward/m-quest-special-reward-detail.component';
import { MQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';

describe('Component Tests', () => {
  describe('MQuestSpecialReward Management Detail Component', () => {
    let comp: MQuestSpecialRewardDetailComponent;
    let fixture: ComponentFixture<MQuestSpecialRewardDetailComponent>;
    const route = ({ data: of({ mQuestSpecialReward: new MQuestSpecialReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestSpecialRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestSpecialRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestSpecialRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestSpecialReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
