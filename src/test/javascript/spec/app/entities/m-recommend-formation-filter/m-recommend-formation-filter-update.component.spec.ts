/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterUpdateComponent } from 'app/entities/m-recommend-formation-filter/m-recommend-formation-filter-update.component';
import { MRecommendFormationFilterService } from 'app/entities/m-recommend-formation-filter/m-recommend-formation-filter.service';
import { MRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';

describe('Component Tests', () => {
  describe('MRecommendFormationFilter Management Update Component', () => {
    let comp: MRecommendFormationFilterUpdateComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterUpdateComponent>;
    let service: MRecommendFormationFilterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRecommendFormationFilterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRecommendFormationFilterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRecommendFormationFilterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRecommendFormationFilter(123);
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
        const entity = new MRecommendFormationFilter();
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
