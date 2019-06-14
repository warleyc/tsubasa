/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStageStoryUpdateComponent } from 'app/entities/m-stage-story/m-stage-story-update.component';
import { MStageStoryService } from 'app/entities/m-stage-story/m-stage-story.service';
import { MStageStory } from 'app/shared/model/m-stage-story.model';

describe('Component Tests', () => {
  describe('MStageStory Management Update Component', () => {
    let comp: MStageStoryUpdateComponent;
    let fixture: ComponentFixture<MStageStoryUpdateComponent>;
    let service: MStageStoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStageStoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MStageStoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MStageStoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStageStoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStageStory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStageStory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
