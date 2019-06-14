/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStoryResourceMappingUpdateComponent } from 'app/entities/m-story-resource-mapping/m-story-resource-mapping-update.component';
import { MStoryResourceMappingService } from 'app/entities/m-story-resource-mapping/m-story-resource-mapping.service';
import { MStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';

describe('Component Tests', () => {
  describe('MStoryResourceMapping Management Update Component', () => {
    let comp: MStoryResourceMappingUpdateComponent;
    let fixture: ComponentFixture<MStoryResourceMappingUpdateComponent>;
    let service: MStoryResourceMappingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoryResourceMappingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MStoryResourceMappingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MStoryResourceMappingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStoryResourceMappingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStoryResourceMapping(123);
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
        const entity = new MStoryResourceMapping();
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
