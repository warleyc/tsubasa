/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestAchievementGroupDetailComponent } from 'app/entities/m-quest-achievement-group/m-quest-achievement-group-detail.component';
import { MQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';

describe('Component Tests', () => {
  describe('MQuestAchievementGroup Management Detail Component', () => {
    let comp: MQuestAchievementGroupDetailComponent;
    let fixture: ComponentFixture<MQuestAchievementGroupDetailComponent>;
    const route = ({ data: of({ mQuestAchievementGroup: new MQuestAchievementGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestAchievementGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestAchievementGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestAchievementGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestAchievementGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
