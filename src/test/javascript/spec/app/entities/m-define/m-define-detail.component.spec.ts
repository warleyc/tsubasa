/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDefineDetailComponent } from 'app/entities/m-define/m-define-detail.component';
import { MDefine } from 'app/shared/model/m-define.model';

describe('Component Tests', () => {
  describe('MDefine Management Detail Component', () => {
    let comp: MDefineDetailComponent;
    let fixture: ComponentFixture<MDefineDetailComponent>;
    const route = ({ data: of({ mDefine: new MDefine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDefineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDefineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDefineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDefine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
