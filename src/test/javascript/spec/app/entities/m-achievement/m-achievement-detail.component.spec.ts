/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAchievementDetailComponent } from 'app/entities/m-achievement/m-achievement-detail.component';
import { MAchievement } from 'app/shared/model/m-achievement.model';

describe('Component Tests', () => {
  describe('MAchievement Management Detail Component', () => {
    let comp: MAchievementDetailComponent;
    let fixture: ComponentFixture<MAchievementDetailComponent>;
    const route = ({ data: of({ mAchievement: new MAchievement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAchievementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAchievementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAchievementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAchievement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
