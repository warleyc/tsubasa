/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestRewardGroupDetailComponent } from 'app/entities/m-quest-reward-group/m-quest-reward-group-detail.component';
import { MQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';

describe('Component Tests', () => {
  describe('MQuestRewardGroup Management Detail Component', () => {
    let comp: MQuestRewardGroupDetailComponent;
    let fixture: ComponentFixture<MQuestRewardGroupDetailComponent>;
    const route = ({ data: of({ mQuestRewardGroup: new MQuestRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
