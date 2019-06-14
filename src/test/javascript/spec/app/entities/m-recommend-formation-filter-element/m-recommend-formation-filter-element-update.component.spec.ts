/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterElementUpdateComponent } from 'app/entities/m-recommend-formation-filter-element/m-recommend-formation-filter-element-update.component';
import { MRecommendFormationFilterElementService } from 'app/entities/m-recommend-formation-filter-element/m-recommend-formation-filter-element.service';
import { MRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';

describe('Component Tests', () => {
  describe('MRecommendFormationFilterElement Management Update Component', () => {
    let comp: MRecommendFormationFilterElementUpdateComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterElementUpdateComponent>;
    let service: MRecommendFormationFilterElementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterElementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRecommendFormationFilterElementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRecommendFormationFilterElementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRecommendFormationFilterElementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRecommendFormationFilterElement(123);
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
        const entity = new MRecommendFormationFilterElement();
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
