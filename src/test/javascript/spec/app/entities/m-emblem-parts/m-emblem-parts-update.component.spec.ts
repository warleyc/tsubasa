/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemPartsUpdateComponent } from 'app/entities/m-emblem-parts/m-emblem-parts-update.component';
import { MEmblemPartsService } from 'app/entities/m-emblem-parts/m-emblem-parts.service';
import { MEmblemParts } from 'app/shared/model/m-emblem-parts.model';

describe('Component Tests', () => {
  describe('MEmblemParts Management Update Component', () => {
    let comp: MEmblemPartsUpdateComponent;
    let fixture: ComponentFixture<MEmblemPartsUpdateComponent>;
    let service: MEmblemPartsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemPartsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEmblemPartsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEmblemPartsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEmblemPartsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEmblemParts(123);
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
        const entity = new MEmblemParts();
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
