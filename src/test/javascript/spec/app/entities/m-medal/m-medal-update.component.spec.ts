/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMedalUpdateComponent } from 'app/entities/m-medal/m-medal-update.component';
import { MMedalService } from 'app/entities/m-medal/m-medal.service';
import { MMedal } from 'app/shared/model/m-medal.model';

describe('Component Tests', () => {
  describe('MMedal Management Update Component', () => {
    let comp: MMedalUpdateComponent;
    let fixture: ComponentFixture<MMedalUpdateComponent>;
    let service: MMedalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMedalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMedalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMedalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMedalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMedal(123);
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
        const entity = new MMedal();
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
