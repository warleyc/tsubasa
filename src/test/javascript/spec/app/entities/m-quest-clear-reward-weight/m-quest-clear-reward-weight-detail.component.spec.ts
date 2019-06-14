/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestClearRewardWeightDetailComponent } from 'app/entities/m-quest-clear-reward-weight/m-quest-clear-reward-weight-detail.component';
import { MQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';

describe('Component Tests', () => {
  describe('MQuestClearRewardWeight Management Detail Component', () => {
    let comp: MQuestClearRewardWeightDetailComponent;
    let fixture: ComponentFixture<MQuestClearRewardWeightDetailComponent>;
    const route = ({ data: of({ mQuestClearRewardWeight: new MQuestClearRewardWeight(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestClearRewardWeightDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestClearRewardWeightDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestClearRewardWeightDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestClearRewardWeight).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
