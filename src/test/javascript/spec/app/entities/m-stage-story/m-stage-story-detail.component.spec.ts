/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStageStoryDetailComponent } from 'app/entities/m-stage-story/m-stage-story-detail.component';
import { MStageStory } from 'app/shared/model/m-stage-story.model';

describe('Component Tests', () => {
  describe('MStageStory Management Detail Component', () => {
    let comp: MStageStoryDetailComponent;
    let fixture: ComponentFixture<MStageStoryDetailComponent>;
    const route = ({ data: of({ mStageStory: new MStageStory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStageStoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MStageStoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStageStoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mStageStory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
