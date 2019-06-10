/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalMaterialUpdateComponent } from 'app/entities/m-arousal-material/m-arousal-material-update.component';
import { MArousalMaterialService } from 'app/entities/m-arousal-material/m-arousal-material.service';
import { MArousalMaterial } from 'app/shared/model/m-arousal-material.model';

describe('Component Tests', () => {
  describe('MArousalMaterial Management Update Component', () => {
    let comp: MArousalMaterialUpdateComponent;
    let fixture: ComponentFixture<MArousalMaterialUpdateComponent>;
    let service: MArousalMaterialService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalMaterialUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MArousalMaterialUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MArousalMaterialUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MArousalMaterialService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MArousalMaterial(123);
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
        const entity = new MArousalMaterial();
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
